var express = require('express');
var router = express.Router();

const database = require('../class/database');
const db = new database()

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/login', (req, res, next) => {
  res.render('login');
})

router.post('/login', (req, res, next) => {
  const data = req.body
  console.log(data);
  db.getuser(data.user).then(r => console.log(r));
  res.sendStatus(200)
});

router.post('/register', async (req, res, next) => {
  const data = req.body
  console.log(data);
  const result = await db.insertuser(data.user, data.password)

  res.status(200).json({"success":result})

})
module.exports = router;
