onload = () => {
    document.getElementById("loginbutton").onclick = () => {
        const userdata = {
            user: document.getElementById("userinput").value,
            password: document.getElementById("passwordinput").value
        }

        const request = {
            method: "GET",
            
            //body: JSON.stringify(userdata),
            mode: "no-cors"
        }

        fetch("http://localhost:8080",request)
            .then(
                (value) => console.log(value)
            )
    }
}