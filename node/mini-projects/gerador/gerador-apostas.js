let quantity;
let size;
let body;

onload = () => {
    alert("Acordou");
    quantity = document.getElementById("quantityInput");
    size     = document.getElementById("sizeInput");
    body     = document.getElementById("result")

    let button = document.getElementById("button");

    button.onclick = () => {
        generate(quantity.value, size.value).forEach(
            element => console.log(element)
        );

        generate(quantity.value, size.value).forEach(
            element => body.innerText = body.innerText + element + ","
        )
    };
}

function generate(quantity, size) {
    let list = [];
    for (let i = 0; i < quantity; i++) {
        let n;
        do {
            n = Math.floor(Math.random() * size + 1);
        } while(list.includes(n) == true);
        list.push(n);
    }
    return list;
}
