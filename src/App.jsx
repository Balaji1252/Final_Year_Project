import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ChatApp from './Components/ChatApp'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div><ChatApp/>
        
      </div>
      
    </>
  )
}

export default App
