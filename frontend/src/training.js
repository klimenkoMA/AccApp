import express from 'express';
import jwt from 'jsonwebtoken';
import mongoose from 'mongoose';

mongoose.connect('mongodb://localhost:27018/upcdocs')
    .then(() => {
        console.log('DB connected')
            .catch(() => 'DB error', err);
    });

console.log("bugaga");

const app = express();

app.use(express.json());

app.get('/', (req, res) => {
    res.send('Сайт для кисиных томатиков! В процессе разработки ' +
        'Вот томатик, и вот томатик!');
});

app.post('/auth/login', (req, res) => {
    console.log(req.body);

    const token = jwt.sign({
            email: req.body.email,
            fullName: "Vasya Pupkin"
        }, 'secret123',
    );

    res.json({
        success: true,
        token
    });

});

app.listen(4444, (err) => {
    if (err) {
        return console.log(err);
    }

    console.log('Server OK');
});