import PostModel from '../models/Post.js';

export const create = async (req, res) => {
    console.log('start in postController');
    try {
        const doc = new PostModel({
            title: req.body.title,
            text: req.body.text,
            imageUrl: req.body.imageUrl,
            tags: req.body.tags,
            user: req.userId,
        });

        console.log(doc);
        const post = await doc.save();

        console.log(post);
        res.json(post);

    } catch (err) {
        console.log(err);
        res.status(500).json({
            message: 'Could not create an article',
        });
    }
};