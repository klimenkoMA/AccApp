import {validationResult} from "express-validator";

export default (req, res, next) => {

    try{
        const errors = validationResult(req);

        if(!errors.isEmpty()){
            return res.status(404).json(errors.array());
        }

        next();
    }catch (e) {
        console.log(e);
        return res.status(500).json({
           message: 'Wrong validation'
        });
    }

};