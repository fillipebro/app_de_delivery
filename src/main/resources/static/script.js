const restaurants = [
  {
    name: "Vegano Fast",
    eco: true,
    description: "100% vegano e entrega por bike.",
    image: "img/vegano.jpg",
    price: "R$ 22,90"
  },
  {
    name: "Pizza Spark",
    eco: false,
    description: "Pizzas quentinhas rápido na sua casa",
    image: "img/pizza.jpg",
    price: "R$ 39,90"
  },
  {
    name: "Eco Sushi",
    eco: true,
    description: "Ingredientes orgânicos e embalagens recicláveis.",
    image: "img/sushi.jpg",
    price: "R$ 49,00"
  },
  {
    name: "Burger Boom com fritas",
    eco: false,
    description: "Clássicos hambúrgueres com batata.",
    image: "img/hamburguer.jpg",
    price: "R$ 29,50"
  },
  {
    name: "Suco Natural Mix",
    eco: true,
    description: "Sucos naturais e orgânicos variados.",
    image: "img/sucos.jpg",
    price: "R$ 12,00"
  },
  {
    name: "Refrigerante",
    eco: false,
    description: "Sua bebida favorita sempre gelada.",
    image: "img/refri.jpg",
    price: "R$ 6,50"
  }
]


const restaurantList = document.getElementById("restaurantList");
const ecoSwitch = document.getElementById("ecoSwitch");

function renderRestaurants(ecoOnly = false) {
  restaurantList.innerHTML = "";
  const filtered = ecoOnly ? restaurants.filter(r => r.eco) : restaurants;

  filtered.forEach(r => {
    const card = document.createElement("div");
    card.className = "col-md-6 col-lg-4 mb-4";
    card.innerHTML = `
      <div class="card h-100 ${r.eco ? 'card-eco' : ''}">
        <img src="${r.image}" class="card-img-top" alt="${r.name}" style="height: 200px; object-fit: cover;">
        <div class="card-body">
          <h5 class="card-title">${r.name}</h5>
          <p class="card-text">${r.description}</p>
          <p class="card-text fw-bold">${r.price}</p>
          ${r.eco ? '<span class="badge bg-success">Eco</span>' : ''}
          <button class="btn btn-primary mt-2 add-to-cart" data-name="${r.name}" data-price="${r.price}">Adicionar ao Carrinho</button>
        </div>
      </div>
    `;
    restaurantList.appendChild(card);
  });
}


ecoSwitch.addEventListener("change", () => {
  renderRestaurants(ecoSwitch.checked);
});


document.addEventListener("click", function(e) {
  if (e.target.classList.contains("add-to-cart")) {
    const name = e.target.dataset.name;
    const price = e.target.dataset.price;
    const cart = JSON.parse(localStorage.getItem("cart")) || [];
    cart.push({ name, price });
    localStorage.setItem("cart", JSON.stringify(cart));
    alert(`${name} foi adicionado ao carrinho!`);
  }
});

renderRestaurants();
