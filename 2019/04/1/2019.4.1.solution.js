const {
  getValidPasswordsTotal,
  hasLengthOfSix,
  hasNoDescendingItem,
  hasRepeatingItem
} = require('../2019.4.common');

const validityFns = [hasLengthOfSix, hasNoDescendingItem, hasRepeatingItem];

const solution = getValidPasswordsTotal(validityFns);

module.exports = solution;
