import React, { useRef, useCallback, useState }  from 'react';

const App = () => {
  const nextId = useRef(1);
  const [form, setForm] = useState({ name: '', username: ''});
  const [data, setData] = useState({
    array:[],
    uselessValue: null
  });
  const onChange = useCallback(
    e => {
      const {name, value} = e.target;
      setForm({
        ...form,
        [name]: [value]
      });
    },[form]
  );

  const onSubmit = useCallback(
    e => {
      e.preventDefault();
      const info = {
        id: nextId.current,
        name: form.name,
        username: form.username
      };
      setData({
        ...data,
        array: data.array.concat(info)
      });
      
    }
  )
}

export default App;
