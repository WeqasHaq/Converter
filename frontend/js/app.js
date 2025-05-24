document.getElementById('convertBtn').addEventListener('click', async () => {
  const from = document.getElementById('from').value;
  const to = document.getElementById('to').value;
  const amount = document.getElementById('amount').value;

  try {
    const res = await fetch(`http://localhost:8080/currency-converter-backend/api/convert?from=${from}&to=${to}&amount=${amount}`);
    const data = await res.json();
    document.getElementById('result').innerText = `${data.amount} ${data.from} = ${data.result} ${data.to}`;
  } catch (err) {
    document.getElementById('result').innerText = 'Error fetching conversion';
    console.error(err);
  }
});
