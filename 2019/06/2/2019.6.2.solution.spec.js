const solution = require('./2019.6.2.solution.js');
const input = require('../2019.6.input.js');

describe('2019.6.2.solution', () => {
  it('solves the problem', () => {
    const actual = solution(input);
    const expected = 445;
    expect(actual).toBe(expected);
  });
});
