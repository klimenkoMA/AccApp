import mongoose from 'mongoose';

const PostSchema = new mongoose.Schema({
        title: {
            type: String,
            required: true,
        },
        text: {
            type: String,
            required: true,
        },
        tags: {
            type: String,
            default: '',
        },
        viewsCount: {
            type: Number,
            default: 0,
        },
        user: {
            type: mongoose.Schema.Types.ObjectId,
            ref: 'User',
        },
        imageUrl: String,
    },

    {
        timestamps: true,
    }
);

export default mongoose.model('Post', PostSchema);