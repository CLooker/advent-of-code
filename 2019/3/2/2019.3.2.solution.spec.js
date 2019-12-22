const solution = require('./2019.3.2.solution');
const input = require('../2019.3.input');

describe('solution', () => {
  it('calculates the solution', () => {
    const actual = solution(input);
    const expected = 164012;
    expect(actual).toBe(expected);
  });
});
