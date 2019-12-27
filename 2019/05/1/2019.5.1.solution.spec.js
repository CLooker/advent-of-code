const solution = require('./2019.5.1.solution.js');
const input = require('../2019.5.input.js');

describe('2019.5.1.solution', () => {
  it('solves the problem', () => {
    const actual = solution(input);
    const expected = 13978427;
    expect(actual).toBe(expected);
  });
});
