import express from 'express';
import multer from 'multer';
import * as mongoose from "mongoose";
import {registerValidation, loginValidation, postCreateValidation} from './validations.js';
import {checkAuth, handleValidationErrors} from './utils/index.js';
import {UserController, PostController} from './controllers/index.js';

mongoose.set('strictQuery', false);

mongoose.connect('mongodb://localhost:27017/nodedb')
    .then(() => console.log('DB ok'))
    .catch((err) => console.log('DB error', err));

const app = express();

const storage = multer.diskStorage({
    destination: (rec, file, cb) => {
        cb(null, 'D://JAVA/REPOSITORY/AccApp/frontend/src/uploads');
    },
    filename: (rec, file, cb) => {
        cb(null, file.originalname);
    }
});

const upload = multer({storage: storage});

app.use(express.json());

app.use('/uploads', express.static('D://JAVA/REPOSITORY/AccApp/frontend/src/uploads'));
app.post('/upload', checkAuth, upload.single('image'), (req, res) => {
    const imageName = req.file.originalname;
    res.json({
        url: `D://JAVA/REPOSITORY/AccApp/frontend/src/uploads/${imageName}`,
    });
});

app.get('/posts', PostController.getAll);
app.get('/posts/:id', PostController.getOne);
app.post('/posts', checkAuth, postCreateValidation, handleValidationErrors, PostController.create);
app.delete('/posts/:id', checkAuth, PostController.remove);
app.patch('/posts/:id', checkAuth, postCreateValidation, handleValidationErrors, PostController.update);

app.post('/auth/login', loginValidation, handleValidationErrors, UserController.login);
app.post('/auth/register', registerValidation, handleValidationErrors, UserController.register);
app.get('/auth/me', checkAuth, UserController.getMe);


app.listen(4444, (err) => {
    if (err) {
        console.log(err);
        return console.log(err);
    }

    console.log('Server UP');
});