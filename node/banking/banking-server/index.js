const http = require('http')
const { Account, Databaseconnection} = require('./databaseconnection')

const dbconn = new Databaseconnection('root','2005','33060','users')

const server = http.createServer(
    (req,res) => {
        console.log(req.url)
        console.log(req.headers)
        console.log("END")

        if (req.url === "/" && req.method === "POST") {
            
            let data = ''
            
            req.on('data',
                chunk => data+= chunk
            )
            
            req.on('end',
                async () => {
                    console.log(data)
                    let account = Account.arrayparse(await dbconn.authAccount(Account.JSONparse(data)))
                    
                    if(account !== undefined) {
                        res.write(JSON.stringify(account))
                        res.end()
                    }
                }
            )
        }

        if (req.method === "GET") {
            const data = {
                title:"Hello, World!"
            }
            res.write(JSON.stringify(data))
            res.end()
        }
    }
)

server.listen(8080,'192.168.1.103',() => {
    console.log(`Server listening at ${server.address().address}:${server.address().port}`)
})
