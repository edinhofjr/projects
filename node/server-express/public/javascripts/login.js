document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // Impede o envio padrão do formulário
    let userinput = document.getElementById("user");
    let passwordinput = document.getElementById("password");

    let data = {
        user: userinput.value,
        password: passwordinput.value
    }
    console.log(data)

    const response = await fetch('/login', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json',
        }
    });
});

document.getElementById("registerForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    let userinput = document.getElementById("registeruser").value;
    let passwordinput = document.getElementById("registerpassword").value;

    let data = {
        user: userinput,
        password: passwordinput
    }

    fetch('/register', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json',
        }
    }).then(
        async response => {
            let userdata;
            if (response.ok) {
                console.log(await response.json())
            }
        }
    )
})
