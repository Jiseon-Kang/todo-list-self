import React, {useEffect, useState} from 'react';
import './App.css';
import axios from "axios"

function App() {

    const [todoList, setTodoList] = useState<{id: string, content: string}[]>([]);
    const [todo, setTodo] = useState<string>('');

    const addTodo = async () => {
        await axios.post('/todo', {content: todo})
        setTodo("")
        getTodo()
    }

    const getTodo = async () => {
        const response = await axios.get('/todo')
        const todoArray = response.data
        setTodoList(todoArray)
    }

    const deleteTodo = async (id: string) =>{
        axios.delete(`/todo/${id}`)
        const result = todoList.filter((todo) => {
            return todo.id !== id
        })
        setTodoList(result)
    }

    useEffect(() => {
        getTodo()
    }, [])

    return (
        <div className="App">
            <input
                value={todo}
                onChange={(event) => {
                    const value = event.target.value
                    setTodo(value)
                }}
                onKeyDown={(event) => {
                    if (event.key === 'Enter') {
                        addTodo()
                    }
                }}
            />
            <button onClick={() => {
                addTodo()
            }}>
                확인
            </button>
            {todoList.map((todo, index) => {
                return <div key={index}>
                    <div>{todo.content}</div>
                    <button onClick={() => {
                        deleteTodo(todo.id)
                    }}>
                    삭제
                    </button>
                </div>
            })}
        </div>
    );
}

    export default App;
