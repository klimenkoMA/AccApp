import express from 'express';

console.log("bugaga");

const app = express();

app.get('/', (req, res) => {
    res.send('Сайт для кисиных томатиков! В процессе разработки ' +
        'Вот томатик, и вот томатик!');
});

app.post('/auth/login', (req, res) => {
    res.json({
       success:true,
    });

});

app.listen(4444, (err) => {
    if (err) {
        return console.log(err);
    }

    console.log('Server OK');
});