const http = require('http')

const server = http.createServer(
    (req,res) => {
        console.log("Requisição Feita.")
        res.statusCode = 200
        res.write("<h1>teste</h1>")
        res.end()
    }
)

server.listen(8080,"192.168.1.103")
