document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // Impede o envio padrão do formulário

    let userinput = document.getElementById("user");
    let passwordinput = document.getElementById("password");

    let data = {
        user: userinput.value,
        password: passwordinput.value
    };

    try {
        fetch('/login', {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(
            res => {
                window.location.href = res.url
            }
        )

    } catch (error) {
        console.log("Erro ao fazer a requisição:", error);
    }
});

document.getElementById("registerForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    let userinput = document.getElementById("registeruser").value;
    let passwordinput = document.getElementById("registerpassword").value;

    let data = {
        user: userinput,
        password: passwordinput
    };

    try {
        const response = await fetch('/register', {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            console.log(await response.text());
        } else {
            console.log("Erro HTTP:", response.statusText);
        }
    } catch (error) {
        console.log("Erro ao fazer a requisição:", error);
    }
});
