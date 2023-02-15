import React from 'react';
import {render, screen, waitFor} from '@testing-library/react';
import App from './App';
import userEvent from "@testing-library/user-event";
import axios from "axios";
import {act} from "react-dom/test-utils";

describe('App Tests', () => {

    beforeEach(() => {
        jest.spyOn(axios, 'get').mockResolvedValue({data: []})
    });

    test('화면에 텍스트박스와 버튼이 보인다', async () => {
        await act(() => render(<App/>))

        expect(screen.getByRole("textbox")).toBeInTheDocument()
        expect(screen.getByRole("button",{name:'확인'})).toBeInTheDocument()
    });


    test('서버에 저장되었던 데이터를 볼 수 있다.', async () => {
        const response = {
            data: [{id: '123', content: 'Hello World'}]
        }
        jest.spyOn(axios, 'get').mockResolvedValue(response)
        await waitFor(() => render(<App/>))

        expect(await screen.findByText('Hello World')).toBeInTheDocument()
    })

    test('서버에 저장되었던 데이터를 삭제할 수 있다.', async () => {
            const response = {
                data: [{id: '0002', content: 'Hello'}]
            }
            jest.spyOn(axios, 'get').mockResolvedValue(response)
            const spy = jest.spyOn(axios, 'delete').mockResolvedValue(null)
            await waitFor(() => render(<App/>))

            await userEvent.click(screen.getAllByRole("button", {name: '삭제'})[0])

            expect(await screen.queryByText('Hello')).not.toBeInTheDocument()
            expect(spy).toHaveBeenCalledWith('/todo/0002')
        }
    )

    describe('Add Todo Tests', () => {

        beforeEach(() => {
            jest.spyOn(axios, 'post').mockResolvedValue({})
        })

        test('값을 입력 후 엔터를 누르면 입력했던 값들이 서버에 저장된다', async () => {
            const axiosPost = jest.spyOn(axios, 'post').mockResolvedValue({})
            await act(() => render(<App/>))

            await userEvent.type(screen.getByRole("textbox"), "hello")
            await userEvent.type(screen.getByRole("textbox"), '{enter}')

            expect(axiosPost).toHaveBeenCalledWith('/todo', {content: "hello"})
        })

        test('값을 입력 후 클릭, 값을 입력 후 엔터를 누르면 입력했던 값들이 보인다', async () => {
            await act(() => render(<App/>))

            await userEvent.type(screen.getByRole("textbox"), "hello")
            await userEvent.type(screen.getByRole("textbox"), '{enter}')
            await userEvent.type(screen.getByRole("textbox"), "hi")
            await userEvent.click(screen.getByRole("button",{name:'확인'}))

            expect(screen.getByText("hello")).toBeInTheDocument()
            expect(screen.getByText("hi")).toBeInTheDocument()
        })
    })
})
