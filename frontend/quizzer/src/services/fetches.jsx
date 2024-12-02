export function getAllQuizzes() {
  return fetch(`${import.meta.env.VITE_BACKEND_URL}/api/quizzes`).then((response) =>
    response.json()
  );
}

export function getCategoryByID(categoryId) {
  return fetch(`${import.meta.env.VITE_BACKEND_URL}/api/categories/${categoryId}`).then((response) =>
    response.json()
  );
}

export function getQuizByID(quizId) {
  return fetch(`${import.meta.env.VITE_BACKEND_URL}/api/quizzes/${quizId}`).then((response) =>
    response.json()
  );
}

export function getAllCategories() {
  return fetch(`${import.meta.env.VITE_BACKEND_URL}/api/categories`).then((response) =>
    response.json()
  );
}

export function getResultsByQuizID(quizId) {
  return fetch(`${import.meta.env.VITE_BACKEND_URL}/api/seeresults/${quizId}`).then((response) =>
    response.json()
  );
}

export function getAnswersByQuestionID(questionId) {
  return fetch(`${import.meta.env.VITE_BACKEND_URL}/api/answers/${questionId}`).then((response) =>
    response.json()
  );
}