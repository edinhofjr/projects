const { response } = require('express');
const http = require('http');
const { parse } = require('path');

const options = {
    port: 8080,
    host: 'localhost',
    method: 'GET'
}

const req = http.request(options);
req.end();

req.on('response',
    (response) => {
        let data = '' 

        response.on('data',
            chunk => data+=chunk
        )

        response.on('end',
            () => {
                console.log(JSON.parse(data).title)
            }
        )
    })
