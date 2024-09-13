const mysqlx = require("@mysql/xdevapi")

class database {
    constructor() {
        this.tableaccount = null;
        this.connect()
    }

    async connect() {
        try {
            this.session = await mysqlx.getSession(
                {
                    user: 'root',
                    password: '2005',
                    port: 33060,
                    schema: "banking"
                }
            )
            console.log("Connected");
        } catch (error) {
            console.error(error)
        }
    }

     /**
      * @param {string} user
      * @return {Promise<mysqlx.Scalar[],undefined>} userdata
      */

     async getuser(user) {
         let sqlExecute = await this.session.sql("SELECT * FROM accounts WHERE user = ? ;").bind(user).execute()
         return sqlExecute.fetchOne();
    }

    /**
     * @param {string} user
     * @param {string} password
     * @return {Promise<boolean>} inserted?
     */
    async insertuser(user, password) {
        return this.getuser(user).then(
            value => {
                if (value === undefined) {
                    this.session.sql("INSERT INTO accounts VALUES( ? , ? )").bind(user,password).execute();
                    return true;
                } else {
                    return false;
                }
            }
        )
    }
}

module.exports = database