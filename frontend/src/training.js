import express from 'express';
import bcrypt from 'bcrypt';
import mongoose from 'mongoose';
import {registerValidation} from "./validations/auth.js";
import {validationResult} from 'express-validator';
import UserModel from "./models/User.js";


mongoose.connect('mongodb://localhost:27017/reactdb')
    .then(() => {
        console.log('DB connected');
            // .catch(() => 'DB error', err);
    });

const app = express();

app.use(express.json());

app.get('/', (req, res) => {
    res.send('Сайт для кисиных томатиков! В процессе разработки ' +
        'Вот томатик, и вот томатик!');
});

app.post('/auth/register', registerValidation, async (req, res) => {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        return res.status(400).json(errors.array());
    }

    const password = req.body.password;
    const salt = await bcrypt.genSalt(10);
    const passwordHash = await bcrypt.hash(password, salt);

    const doc = new UserModel({
        email: req.body.email,
        fullName: req.body.fullName,
        passwordHash,
        avatarUrl: req.body.avatarUrl,
    });

    const user = await doc.save();
    console.log("User successfully logged");
});


app.listen(4444, (err) => {
    if (err) {
        return console.log(err);
    }
    console.log('Server OK');
});