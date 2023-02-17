import React, {useEffect, useState} from 'react';
import './App.css';
import axios from "axios"

function App() {

    const [todoList, setTodoList] = useState<{ id: string, content: string }[]>([]);
    const [todo, setTodo] = useState<string>('');
    const [editId, setEditId] = useState<string>('');
    const [todoUpdate,setTodoUpdate] = useState<string>('');


    const addTodo = async () => {
        todo.length === 0 ? window.alert('빈칸안돼~~') : await axios.post('/todo', {content: todo})
        setTodo("")
    }

    const getTodo = async () => {
        const response = await axios.get('/todo')
        const todoArray = response.data
        setTodoList(todoArray)
    }

    const deleteTodo = async (id: string) => {
        await axios.delete(`/todo/${id}`)
        const result = todoList.filter((todo) => {
            return todo.id !== id
        })
        setTodoList(result)
    }

    const updateTodo = async (id: string) => {
        todoUpdate.length === 0 ? window.alert('빈칸안돼애애애') : await axios.put(`/todo/${id}`,{'content' : todoUpdate})

        setEditId("")
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
                    {editId === todo.id ? (<div>
                        <input
                        value={todoUpdate}
                        onChange={(event) => {
                            const value = event.target.value
                            setTodoUpdate(value)
                        }}
                        onKeyDown={(event) => {
                            if (event.key === 'Enter') {
                                updateTodo(todo.id)
                            }
                        }}
                    />
                        <button onClick={() => {
                            updateTodo(todo.id)
                        }}>
                            수정
                        </button>
                    </div>) : (
                        <div>
                            {todo.content}
                        </div>)}
                    <button onClick={() => {
                        setEditId(todo.id)
                        setTodoUpdate(todo.content)
                    }}>
                        변경
                    </button>
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
