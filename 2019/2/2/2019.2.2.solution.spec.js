const solution = require('./2019.2.2.solution');
const input = require('../2019.2.input');

describe('solution', () => {
  it('calculates the solution', () => {
    const actual = solution(input);
    const expected = '8609';
    expect(actual).toBe(expected);
  });
});
