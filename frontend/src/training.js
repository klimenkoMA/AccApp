import express from 'express';
import * as mongoose from "mongoose";
import {registerValidation, loginValidation} from './validations.js';
import checkAuth from './utils/checkAuth.js';
import * as UserController from './controllers/UserController.js'


mongoose.connect('mongodb://localhost:27017/nodedb')
    .then(() => console.log('DB ok'))
    .catch((err) => console.log('DB error', err));

const app = express();

app.use(express.json());

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