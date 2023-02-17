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

    test('화면에 텍스트박스와 확인 버튼이 보인다', async () => {
        await act(() => render(<App/>))

        expect(screen.getByRole("textbox")).toBeInTheDocument()
        expect(screen.getByRole("button",{name:'확인'})).toBeInTheDocument()
    });


    test('서버에 저장되었던 데이터를 볼 수 있다.', async () => {
        const response = {
            data: [{id:'1',content:'Hello World'}]
        }
        jest.spyOn(axios, 'get').mockResolvedValue(response)
        await waitFor(() => render(<App/>))

        expect(await screen.findByText('Hello World')).toBeInTheDocument()
    })

    test('서버에 저장되었던 데이터를 삭제할 수 있다.', async () => {
            jest.spyOn(axios, 'get').mockResolvedValue({data: [{id:'1',content:'Hello World'}]})
            const spy = jest.spyOn(axios, 'delete').mockResolvedValue(null)
            await waitFor(() => render(<App/>))
            await waitFor(() => expect( screen.getByText('Hello World')).toBeInTheDocument())

            await userEvent.click(screen.getAllByRole("button", {name: '삭제'})[0])

            expect(await screen.queryByText('Hello World')).not.toBeInTheDocument()
            expect(spy).toHaveBeenCalledWith('/todo/1')
        }
    )
    test('서버에 저장되었던 데이터의 변경 버튼을 누르면 텍스트박스 형식으로 변경된다.',async () => {
        jest.spyOn(axios,'get').mockResolvedValue({data: [{id:'1',content:'Hello World'}]})

        await waitFor(() => render(<App/>))
        await waitFor(()=> expect(screen.getByText('Hello World')).toBeInTheDocument())

        await userEvent.click(screen.getAllByRole("button",{name:'변경'})[0])

        expect(await screen.getAllByRole("textbox")[1]).toHaveValue('Hello World')//텍스트박스에 입력했던 값이 보인다.

    })


    describe('Add Todo Tests', () => {

        beforeEach(() => {
            jest.spyOn(axios, 'post').mockResolvedValue({})
        })

        test('값을 입력 후 엔터를 누르면 입력했던 값들이 서버에 저장된다', async () => {
            const axiosPost = jest.spyOn(axios, 'post').mockResolvedValue({})
            await waitFor(() => render(<App/>))

            await userEvent.type(screen.getByRole("textbox"), "hello")
            await userEvent.type(screen.getByRole("textbox"), '{enter}')

            expect(axiosPost).toHaveBeenCalledWith('/todo', {content: "hello"})
        })

        test('값을 입력 후 클릭 입력했던 값들이 보인다', async () => {
            jest.spyOn(axios, 'get').mockResolvedValueOnce({data: [{id:'1',content:'hello'}]})
            await act(() => render(<App/>))

            await userEvent.type(screen.getByRole("textbox"), "hello")
            await userEvent.type(screen.getByRole("textbox"), '{enter}')

            expect(screen.getByText("hello")).toBeInTheDocument()
        })

        test('값을 입력 후 확인버튼을 누르면 입력했던 값들이 보인다', async () => {
            jest.spyOn(axios, 'get').mockResolvedValueOnce({data: [{id:'1',content:'hi'}]})
            await act(() => render(<App/>))

            await userEvent.type(screen.getByRole("textbox"), "hi")
            await userEvent.click(screen.getByRole("button",{name:'확인'}))

            expect(screen.getByText("hi")).toBeInTheDocument()
        })


    })
})
