import QuizList from './components/QuizList';
import Navbar from './components/Navbar'
import CategoryList from './components/CategoryList';
import QuizView from './components/QuizView';
import Container from '@mui/material/Container'
import { Route, Routes } from 'react-router-dom';
import CategoryIdList from './components/CategoryIdList';
import ResultsList from './components/ResultsList';
import Review from './components/Review';
import "./App.css"

function App() {
	return (
		<Container maxWidth={false} disableGutters={true}>
			<Navbar />
			<Routes>
				<Route path="/" element={<QuizList />} />
				<Route path="/quizzes" element={<QuizList />} />
				<Route path="/categories" element={<CategoryList />} />
				<Route path="/questions/:quizId" element={<QuizView />} />
				<Route path="/categories/:categoryId" element={<CategoryIdList />} />
				<Route path="/seeresults/:quizId" element={<ResultsList />} />
				<Route path="/quizzes/:quizId/addReview" element={<Review />} />
			</Routes>
		</Container>
	)
}

export default App
