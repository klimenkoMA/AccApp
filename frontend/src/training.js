import express from 'express';
import * as mongoose from "mongoose";
import {registerValidation, loginValidation} from './validations.js';
import checkAuth from './utils/checkAuth.js';
import * as UserController from './controllers/UserController.js'
import * as PostController from './controllers/PostController.js'
import jwt from "jsonwebtoken";



mongoose.connect('mongodb://localhost:27017/nodedb')
    .then(() => console.log('DB ok'))
    .catch((err) => console.log('DB error', err));

const app = express();

app.use(express.json());


app.get('/', (req, res) =>{
   console.log(req.body);

   res.json({
       success: true,
   });
});

app.post('/test', (req, res) =>{
    const token = jwt.sign({
        email: req.body.email,
        fullName: 'Vasya Pupkin',
    }, 'secret123',
    );

    res.json({
        success: true,
        token,
    });
});
// app.get('/posts', PostController.getAll);
// app.get('/posts/:id', PostController.getOne);
app.post('/posts', PostController.create);
// app.delete('/posts, PostController.remove');
// app.patch('/posts, PostController.update');

app.post('/auth/login', loginValidation, UserController.login);

app.post('/auth/register', registerValidation, UserController.register);

app.get('/auth/me', checkAuth, UserController.getMe);


app.listen(4444, (err) => {
    if (err) {
        console.log(err);
        return console.log(err);
    }

    console.log('Server UP');
});