const solution = require('./2019.3.1.solution');
const input = require('../2019.3.input');

describe('solution', () => {
  it('calculates the solution', () => {
    const actual = solution(input);
    const expected = 4981;
    expect(actual).toBe(expected);
  });
});
