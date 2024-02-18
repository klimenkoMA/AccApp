import {body} from 'express-validator';


export const registerValidation = [
    body('email', 'Wrong email format').isEmail(),
    body('password', 'Password must be at least 5 characters').isLength({min: 5}),
    body('fullName', "Please enter name").isLength({min: 3}),
    body('avatarUrl', 'Wrong avatar link').optional().isURL(),
];

export const loginValidation = [
    body('email', 'Wrong email format').isEmail(),
    body('password', 'Password must be at least 5 characters').isLength({min: 5}),
];

export const postCreateValidation = [
    body('title', 'Input a header of article').isLength({min: 3}).isString(),
    body('text', 'Input a text of article').isLength({min: 10}).isString(),
    body('tags', "Wrong tags format: (point an string)").optional().isString,
    body('imageUrl', 'Wrong image link').optional().isString(),
];

