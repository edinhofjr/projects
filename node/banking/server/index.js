const http = require('http')

const server = http.createServer(
    (req,res) => {
        const data = {
            title:"Hello, World!"
        }
        res.write(JSON.stringify(data))
        res.end()
        console.log("Conexão GET aceita.")
    }
)

server.listen(8080,'localhost',() => {
    console.log(`Server listening at ${server.address().address}:${server.address().port}`)
})
