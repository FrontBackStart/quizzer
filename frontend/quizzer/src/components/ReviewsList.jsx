import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom'; 
import { getReviewsByQuizID } from "../services/fetches";
import { Box, Typography, Card, CardContent, Grid, Rating } from "@mui/material";

const ReviewsList = () => {
    const { quizId } = useParams(); 
    const [reviews, setReviews] = useState([]);
    const [quizName, setQuizName] = useState('');
    const [averageRating, setAverageRating] = useState(0);

    async function fetchReviews () {
        try {
            const data = await getReviewsByQuizID(quizId); // Fetch reviews data
            setQuizName(data.quizName);
            setAverageRating(data.averageRating);
            setReviews(data.reviews);
        } catch (err) {
            setError('Failed to load reviews. Please try again later.');
        }
    }

    useEffect(() => {
        fetchReviews();
    }, [quizId]);

    return (
    <Box sx={{ p: 3 }}>
        <Typography variant="h4" gutterBottom>
            Reviews for {quizName}
        </Typography>
        <Typography variant="h6" gutterBottom>
            Average Rating: <Rating value={averageRating} precision={0.1} readOnly /> ({averageRating.toFixed(1)}/5)
        </Typography>
    {reviews.length === 0 ? (
        <Typography>This Quiz doesn't have any reviews yet.</Typography>
    ): 
    (
        <Grid container spacing={2}>
            {reviews.map((review) => (
                <Grid item xs={12} sm={6} md={4} key={review.reviewId}>
                <Card>
                    <CardContent>
                        <Typography variant="h6">{review.nickname}</Typography>
                        <Rating value={review.rating} readOnly />
                        <Typography variant="body2" sx={{ mt: 1 }}>
                            {review.reviewText}
                        </Typography>
                        <Typography variant="caption" color="textSecondary">
                            Reviewed on: {new Date(review.createdAt).toLocaleDateString('de-DE')}
                        </Typography>
                    </CardContent>
                </Card>
                </Grid>
            ))}
        </Grid>
    )}
    </Box>
  );
};

export default ReviewsList;