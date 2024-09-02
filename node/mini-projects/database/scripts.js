onload = () => {
    document.getElementById('loginbutton').onclick = () => {

        const user = {
            user: document.getElementById("userInput").value,
            password: document.getElementById("passwordInput").value            
        }
        
        console.log(user.user)
        console.log(user.password)

    }
}