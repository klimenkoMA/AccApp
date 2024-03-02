import express from 'express';
import multer from 'multer';
import * as mongoose from "mongoose";
import {registerValidation, loginValidation, postCreateValidation} from './validations.js';
import checkAuth from './utils/checkAuth.js';
import * as UserController from './controllers/UserController.js'
import * as PostController from './controllers/PostController.js'

mongoose.set('strictQuery', false);

mongoose.connect('mongodb://localhost:27017/nodedb')
    .then(() => console.log('DB ok'))
    .catch((err) => console.log('DB error', err));

const app = express();

const storage = multer.diskStorage({
    destination: (rec, file, cb) => {
        // cb(null, 'D:\\JAVA\\REPOSITORY\\AccApp\\frontend\\src\\uploads');
        cb(null, process.cwd() + '/frontend/src/uploads');

    },
    filename: (rec, file, cb) => {
        cb(null, file.originalname);
    }
});

const upload = multer({ storage: storage });

app.use(express.json());
app.use('/uploads', express.static(process.cwd()+'/uploads'));

app.post('/upload', checkAuth, upload.single('image'), (req, res) => {
    res.json({
        url: process.cwd() + '/frontend/src/uploads/${req.file.originalname}',
    });
});



app.get('/posts', PostController.getAll);
app.get('/posts/:id', PostController.getOne);
app.post('/posts', checkAuth, postCreateValidation, PostController.create);
app.delete('/posts/:id', checkAuth, PostController.remove);
app.patch('/posts/:id', checkAuth, PostController.update);



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