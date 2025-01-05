import React, { useState } from 'react';

import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';

function App() {
    const [coins, setCoins] = useState([]);

    const [error, setError] = useState('');

    const [targetAmount, setTargetAmount] = useState('');
    const [denominations, setDenominations] = useState('');
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();

        setLoading(true);
        setError('');
        setCoins([]);

        const coinArray = denominations
            .split(',')
            .map((item) => item.trim())
            .filter(Boolean)
            .map((num) => parseFloat(num));

        try {
            const res = await axios.post('/coinchange', {
                targetAmount: parseFloat(targetAmount),
                coinDenominations: coinArray,
            });
            setCoins(res.data.coins); 
        } catch (err) {
            if (err.response && err.response.data) {
                setError(err.response.data);
            } else {
                setError(err.message || 'Unknown error');
            }
        } finally {
            setLoading(false);
        }
    };

    return (
        <>
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                <div className="container">
                    <a className="navbar-brand" href="#!">
                        Coin Change Wizard by Shuoqi YANG
                    </a>
                </div>
            </nav>

            <div className="container my-5">
                <div className="row justify-content-center">
                    <div className="col-md-6">

                        <div className="card shadow">
                            <div className="card-header bg-white">
                                <h4 className="mb-0">Calculate Minimum Coins</h4>
                            </div>
                            <div className="card-body">

                                {error && (
                                    <div className="alert alert-danger" role="alert">
                                        <strong>Error:</strong> {error}
                                    </div>
                                )}
                                

                                <form onSubmit={handleSubmit}>
                                    <div className="mb-3">
                                        <label className="form-label">Target Amount</label>
                                        <input
                                            type="number"
                                            className="form-control"
                                            placeholder="e.g. 7.03"
                                            value={targetAmount}
                                            onChange={(e) => setTargetAmount(e.target.value)}
                                            required
                                        />
                                    </div>
                                    <div className="mb-3">
                                        <label className="form-label">
                                            Coin Denominations (comma-separated)
                                        </label>
                                        <input
                                            type="text"
                                            className="form-control"
                                            placeholder="e.g. 0.01,0.5,1,5,10"
                                            value={denominations}
                                            onChange={(e) => setDenominations(e.target.value)}
                                        />
                                    </div>

                                    <button
                                        type="submit"
                                        className="btn btn-primary"
                                        disabled={loading}
                                    >
                                        {loading ? (
                                            <>
                        <span
                            className="spinner-border spinner-border-sm me-2"
                            role="status"
                            aria-hidden="true"
                        />
                                                Calculating...
                                            </>
                                        ) : (
                                            'Calculate'
                                        )}
                                    </button>
                                </form>

                                {coins.length > 0 && (
                                    <div className="mt-4">
                                        <h5>Result Coins:</h5>
                                        <ul className="list-group">
                                            {coins.map((coin, index) => (
                                                <li key={index} className="list-group-item">
                                                    {coin}
                                                </li>
                                            ))}
                                        </ul>
                                    </div>
                                )}

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default App;