const solution = require('./solution');
const input = require('.././input');

describe('solution', () => {
  it('calculates the solution', () => {
    const actual = solution(input);
    const expected = '8609';
    expect(actual).toBe(expected);
  });
});
