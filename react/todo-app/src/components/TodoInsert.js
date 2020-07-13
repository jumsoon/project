import React, { useState, useCallback } from 'react';
import { MdCheck } from 'react-icons/md';
import './TodoInsert.scss';

const TodoInsert = ({onInsert}) => {
    const [value, setValue] = useState('');

    const onChange = useCallback(e => {
        setValue(e.target.value);
    },[]);

    const onSubmit = useCallback(
        e => {
            onInsert(value);
            setValue('');
            e.preventDefault();
        },
        [onInsert, value],
    );

    // const onClick = useCallback(
    //     () => {
    //         onInsert(value);
    //         setValue('');
    //     },
    //     [onInsert, value],
    // ); //onkeypress 이벤트가 필요함

    return (
        <form className="TodoInsert" onSubmit={onSubmit}>
            <input
                placeholder="할 일을 입력하세요"
                value={value}
                onChange={onChange}
            />
            <button type="submit">
                <MdCheck />
            </button>
        </form>
    );
};

export default TodoInsert;