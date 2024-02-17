import jwt from 'jsonwebtoken';

export default (req, res, next) => {

        const token = req.headers.authorization;

        res.send(token);

}