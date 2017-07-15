import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

// 保证组件可以正常渲染
it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<App />, div);
});
