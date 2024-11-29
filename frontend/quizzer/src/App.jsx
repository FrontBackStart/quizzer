import QuizList from './components/QuizList';
import Navbar from './components/Navbar'
import CategoryList from './components/CategoryList';
import QuizView from './components/QuizView';
import Container from '@mui/material/Container'
import { Route, Routes } from 'react-router-dom';
import CategoryIdList from './components/CategoryIdList';
import ResultsList from './components/ResultsList';


function App() {
	return (
		<Container maxWidth="xl">
			<Navbar />
			<Routes>
				<Route path="/" element={<QuizList />} />
				<Route path="/quizzes" element={<QuizList />} />
				<Route path="/categories" element={<CategoryList />} />
				<Route path="/questions/:quizId" element={<QuizView />} />
				<Route path="/categories/:categoryId" element={<CategoryIdList />} />
				<Route path="/seeresults/:quizId" element={<ResultsList />} />
			</Routes>
		</Container>
	)
}

export default App
