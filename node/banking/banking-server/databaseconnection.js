const mysql = require('@mysql/xdevapi')


class Databaseconnection {
    /** @type {mysql.Session} #session */
    #session;

    /** @type {mysql.Table} */
    #userTable;

    constructor(user, password, port, schema) {
        mysql.getSession(
            {
                user:'root',
                password:'2005',
                port:33060,
                schema:'users'
            }
        ).then(
            sessiontemp => {
                this.#session = sessiontemp
                this.#userTable = this.#session.getSchema("users").getTable("USERS")
            }
        )
    }

    async authAccount(account) {
        let result = await this.#userTable
            .select()
            .where('name = :name and password = :password').bind("name","edio").bind("password",account.password).execute()
        
        return result.fetchOne()
    }
}

class Account {
    constructor(user, password) {
        this.user = user;
        this.password = password;
    }

    static arrayparse(data) {
        if (!Array.isArray(data)) {
            throw new Error("Expected an Array[2].")
        }
        return new Account(data[0], data[1]);

    }
    static JSONparse(JSONString) {
        let j = JSON.parse(JSONString)
        return new Account(j.user, j.password)
    }

}

module.exports = {Account, Databaseconnection}