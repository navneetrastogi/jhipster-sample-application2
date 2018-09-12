import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IInstruction, defaultValue } from 'app/shared/model/instruction.model';

export const ACTION_TYPES = {
  FETCH_INSTRUCTION_LIST: 'instruction/FETCH_INSTRUCTION_LIST',
  FETCH_INSTRUCTION: 'instruction/FETCH_INSTRUCTION',
  CREATE_INSTRUCTION: 'instruction/CREATE_INSTRUCTION',
  UPDATE_INSTRUCTION: 'instruction/UPDATE_INSTRUCTION',
  DELETE_INSTRUCTION: 'instruction/DELETE_INSTRUCTION',
  RESET: 'instruction/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IInstruction>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type InstructionState = Readonly<typeof initialState>;

// Reducer

export default (state: InstructionState = initialState, action): InstructionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INSTRUCTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INSTRUCTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_INSTRUCTION):
    case REQUEST(ACTION_TYPES.UPDATE_INSTRUCTION):
    case REQUEST(ACTION_TYPES.DELETE_INSTRUCTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_INSTRUCTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INSTRUCTION):
    case FAILURE(ACTION_TYPES.CREATE_INSTRUCTION):
    case FAILURE(ACTION_TYPES.UPDATE_INSTRUCTION):
    case FAILURE(ACTION_TYPES.DELETE_INSTRUCTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_INSTRUCTION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_INSTRUCTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_INSTRUCTION):
    case SUCCESS(ACTION_TYPES.UPDATE_INSTRUCTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_INSTRUCTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/instructions';

// Actions

export const getEntities: ICrudGetAllAction<IInstruction> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_INSTRUCTION_LIST,
  payload: axios.get<IInstruction>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IInstruction> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INSTRUCTION,
    payload: axios.get<IInstruction>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IInstruction> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INSTRUCTION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IInstruction> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INSTRUCTION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IInstruction> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INSTRUCTION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
