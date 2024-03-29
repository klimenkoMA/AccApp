import PostModel from '../models/Post.js';

export const getAll = async (req, res) => {
    try {

        const posts = await PostModel.find().populate('userId').exec();

        res.json(posts);

    } catch (err) {
        console.log(err);
        return res.status(500).json({
            message: "Could not get the articles",
        });
    }
}

export const create = async (req, res) => {

    try {
        const doc = new PostModel({
            title: req.body.title,
            text: req.body.text,
            imageUrl: req.body.imageUrl,
            tags: req.body.tags,
            userId: req.userId,
        });

        const post = await doc.save();

        res.json(post);
    } catch (err) {
        console.log(err);
        res.status(500).json({
            message: 'Could not create an article',
        });
    }
};

export const getOne = async (req, res) => {
    try {
        const postId = req.params.id;

        PostModel.findOneAndUpdate({
                _id: postId,

            }, {
                $inc: {
                    viewsCount: 1
                }
            },
            {
                returnDocument: 'after'
            },
            (err, doc) => {
                if (err) {
                    console.status(500).log(err);
                    return res.json({
                        message: 'Failed to response the article'
                    });
                }
                if (!doc) {
                    return res.status(404).json({
                        message: "Document not found"
                    });
                }

                res.json(doc);
            }
        );

    } catch (e) {
        console.log(e);
        res.status(500).json({
            message: "Failed to get article",
        });
    }

}

export const remove = async (req, res) => {
    try {

        const postId = req.params.id;
        PostModel.findOneAndDelete({
                _id: postId
            }, {
                returnDocument: 'after'
            },
            (err, doc) => {
                if (err) {
                    return res.status(500).json({
                        message: "Something wrong with removing the article"
                    });
                }

                if (!doc) {
                    return res.status(404).json({
                        message: "Could not find the article"
                    });
                }

                res.json({
                    success: true,
                });
            }
        );

    } catch (e) {
        console.log(e);
        res.status(500).json({
            message: 'Something wrong with removing the article'
        });
    }

}

export const update = async (req, res) => {

    try {
        const postId = req.params.id;

        await PostModel.updateOne({
                _id: postId
            },
            {
                title: req.body.title,
                text: req.body.text,
                imageUrl: req.body.imageUrl,
                tags: req.body.tags,
                userId: req.userId,
            });

        res.json({
            success: true,
        });
    } catch (e) {
        console.log(e);
        res.status(500).json({
            message: "Could not update the article",
        });
    }
}