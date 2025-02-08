# Batch Execution Guide

The project includes three main batches:

- `cbtrn02c`
- `cbact04c`
- `prepdata`

## Batch Description

- **prepdata**: Used to clear previous data and reload initial data.
- **cbtrn02c**, **cbact04c**: Corresponding job batches.

## How to Run Batches

Use the `/batch` API with the `POST` method, where the body contains:

```json
{
  "batchName": "..."
}
```

Example to run the `prepdata` batch:

```json
{
  "batchName": "prepdata"
}
```

### Important Notes

- Always run the `prepdata` batch before running other job batches.
- After `prepdata` successfully runs, proceed with `cbtrn02c` or `cbact04c`.

---

If you have any questions, please contact the development team!
