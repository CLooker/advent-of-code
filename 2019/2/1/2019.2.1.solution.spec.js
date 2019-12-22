const solution = require('./2019.2.1.solution');
const input = require('../2019.2.input');

describe('solution', () => {
  it('calculates the solution', () => {
    const actual = solution(input);
    const expected = 3706713;
    expect(actual).toBe(expected);
  });
});
