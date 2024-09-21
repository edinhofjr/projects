var express = require('express');
var router = express.Router();

const database = require('../class/database');
const db = new database()

router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/login', (req, res, next) => {
  res.render('login');
})

router.get('/home', (req, res, next) => {
  res.render('home', { user: "edio"})
})
router.post('/login', async (req, res, next) => {
  const data = req.body

  console.log(data);

  const r = await db.getuser(data.user)

  res.redirect('/home')
});

router.post('/register', async (req, res, next) => {
  const data = req.body
  console.log(data);
  const result = await db.insertuser(data.user, data.password)

  res.status(200).json({"success":result})

})

module.exports = router;
