const userdata = {
    name: "edio",
    password: "1234"
}

const request = {
    method: "POST",  
    body: JSON.stringify(userdata),
    mode: "no-cors"
}

fetch("http://192.168.1.103:8080/",request).then(
    async resp => {
        let json = await resp.text()
        let obj = JSON.parse(json)
        console.log(obj.user, obj.password)
    }
)
