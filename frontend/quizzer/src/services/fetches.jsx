export function getAllQuizzes() {
  return fetch(`${import.meta.env.VITE_BACKEND_URL}/api/quizzes`).then((response) =>
    response.json()
  );
}