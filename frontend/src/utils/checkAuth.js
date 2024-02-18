import jwt from 'jsonwebtoken';

export default (req, res, next) => {

    const token = (req.headers.authorization || '').replace(/Bearer\s?/, '');
    // const token = req.headers.authorization || '';

    console.log(token);
    if (token) {
        try {
            const decoded = jwt.verify(token, 'secret123');
            req.userId = decoded._id;
            next();
        } catch (err) {
            console.log(err);
            return res.status(403).json({
                message: 'No access no access'
            });
        }
    } else {
        return res.status(403).json({
            message: 'No access'
        });
    }
}