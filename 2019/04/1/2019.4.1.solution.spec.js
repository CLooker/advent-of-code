const solution = require('./2019.4.1.solution.js');
const input = require('../2019.4.input.js');

describe('2019.4.1.solution', () => {
  it('solves the problem', () => {
    const actual = solution(input);
    const expected = 1154;
    expect(actual).toBe(expected);
  });
});
